package org.apache.stanbol.entityhub.query.clerezza;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.clerezza.rdf.core.MGraph;
import org.apache.clerezza.rdf.core.UriRef;
import org.apache.clerezza.rdf.core.impl.SimpleMGraph;
import org.apache.clerezza.rdf.core.impl.TripleImpl;
import org.apache.stanbol.entityhub.core.query.FieldQueryImpl;
import org.apache.stanbol.entityhub.model.clerezza.RdfRepresentation;
import org.apache.stanbol.entityhub.model.clerezza.RdfValueFactory;
import org.apache.stanbol.entityhub.servicesapi.model.Representation;
import org.apache.stanbol.entityhub.servicesapi.model.rdf.RdfResourceEnum;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RdfResultListTest {
    private final static Logger log = LoggerFactory.getLogger(RdfResultListTest.class);
    /**
     * Providing a sorted Iteration over query results stored in an RDF
     * graph is not something trivial. Therefore this test
     */
    @Test
    public void testRdfResultSorting(){
        SortedMap<Double,RdfRepresentation> sorted = new TreeMap<Double,RdfRepresentation>();
        MGraph resultGraph = new SimpleMGraph();
        RdfValueFactory vf = new RdfValueFactory(resultGraph);
        UriRef resultListNode = new UriRef(RdfResourceEnum.QueryResultSet.getUri());
        UriRef resultProperty = new UriRef(RdfResourceEnum.queryResult.getUri());
        for(int i=0;i<100;i++){
            Double rank;
            do { //avoid duplicate keys
                rank = Math.random();
            } while (sorted.containsKey(rank));
            RdfRepresentation r = vf.createRepresentation("urn:sortTest:rep."+i);
            //link the representation with the query result set
            resultGraph.add(new TripleImpl(resultListNode,resultProperty,r.getNode()));
            r.set(RdfResourceEnum.resultScore.getUri(), rank);
            sorted.put(rank, r);
        }
        RdfQueryResultList resultList = new RdfQueryResultList(new FieldQueryImpl(),
            resultGraph);
        if(log.isDebugEnabled()){
            log.debug("---DEBUG Sorting ---");
            for(Iterator<Representation> it = resultList.iterator();it.hasNext();){
                Representation r = it.next();
                log.debug("{}: {}",r.getFirst(RdfResourceEnum.resultScore.getUri()),r.getId());
            }
        }
        log.debug("---ASSERT Sorting ---");
        for(Iterator<Representation> it = resultList.iterator();it.hasNext();){
            Representation r = it.next();
            Double lastkey = sorted.lastKey();
            Representation last = sorted.get(lastkey);
            Assert.assertEquals("score: "+r.getFirst(RdfResourceEnum.resultScore.getUri())+
                " of Representation "+r.getId()+" is not as expected "+
                last.getFirst(RdfResourceEnum.resultScore.getUri())+" of Representation "+
                last.getId()+"!",r, last);
            sorted.remove(lastkey);
        }
        Assert.assertTrue(sorted.isEmpty());
    }

}
